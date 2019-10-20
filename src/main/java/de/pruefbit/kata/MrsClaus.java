package de.pruefbit.kata;

import java.util.*;
import java.util.concurrent.*;

import static de.pruefbit.kata.WorkPlan.DEFAULT_PRODUCTION_LIMIT;
import static de.pruefbit.kata.WorkPlan.DEFAULT_TEAM_SIZE;

class MrsClaus implements Runnable {
    private static final String DEFAULT_FAMILY = "Default";
    private static final List<String> DEFAULT_FAMILIES = Collections.singletonList(DEFAULT_FAMILY);

    private final Queue<String> cargoList = new LinkedBlockingQueue<>();
    private final SantasSleigh theSleigh = new Sleigh(cargoList);
    private final int teamSize;
    private final int productionLimit;
    private final Map<String, BlockingDeque<Present>> queues = new HashMap<>();
    private final List<ToyMachine> toyMachines = new ArrayList<>();
    private final BlockingDeque<Elf> availableElves = new LinkedBlockingDeque<>();
    private List<String> families;
    private String currentFamily;
    private ExecutorService threads;
    private Set<String> naughtyFamilies = new HashSet<>();

    MrsClaus() {
        this.teamSize = DEFAULT_TEAM_SIZE;
        this.productionLimit = DEFAULT_PRODUCTION_LIMIT;
        this.families = DEFAULT_FAMILIES;
        this.currentFamily = DEFAULT_FAMILY;
    }

    MrsClaus(WorkPlan workPlan) {
        this.teamSize = workPlan.getTeamSize();
        this.productionLimit = workPlan.getProductionLimit();
        this.families = workPlan.getFamilies();
        if (this.families == null) {
            this.families = DEFAULT_FAMILIES;
        }
        this.currentFamily = this.families.get(0);
    }

    @Override
    public void run() {
        setup();
        loop();
    }

    private void setup() {
        ProductionPlan[] plans = loadDistribution(productionLimit, teamSize, families);
        for (int n = 0; n < teamSize; n += 1) {
            toyMachines.add(new ToyMachine(plans[n]));
            availableElves.add(new Elf(this::givePresent, theSleigh::pack, this::callback));
        }
        families.forEach(f -> queues.put(f, new LinkedBlockingDeque<>()));
        threads = Executors.newFixedThreadPool(availableElves.size());
    }

    private void callback(Elf elf) {
        availableElves.addLast(elf);
    }

    private Present givePresent() {
        if (currentFamily == null) {
            return null;
        }
        return queues.get(currentFamily).pollFirst();
    }

    private static ProductionPlan[] loadDistribution(int productionLimit, int teamSize, List<String> families) {
        ProductionPlan[] plans = new ProductionPlan[teamSize];
        for (int n = 0; n < teamSize; n += 1) {
            plans[n] = new ProductionPlan();
        }
        int team = 0;
        int family = 0;
        int flSize = families.size();
        int load = productionLimit * flSize;
        while (load > 0) {
            plans[team].addFamily(families.get(family));
            family = (family + 1) % flSize;
            team = (team + 1) % teamSize;
            load -= 1;
        }
        return plans;
    }

    private void loop() {
        do {
            for (ToyMachine toyMachine : toyMachines) {
                Present present = toyMachine.givePresent();
                if (present != null) {
                    if (!naughtyFamilies.contains(present.getFamily())) {
                        queues.get(present.getFamily()).addLast(present);
                    }
                    break;
                }
            }
            currentFamily = pickNextFamily(currentFamily, queues);
            if (!availableElves.isEmpty()) {
                threads.execute(Objects.requireNonNull(availableElves.pollFirst()));
            }
        } while (currentFamily != null);
        threads.shutdown();
        try {
            threads.awaitTermination(3000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
    }

    private static String pickNextFamily(String currentFamily, Map<String, BlockingDeque<Present>> queues) {
        if (currentFamily != null && !queues.get(currentFamily).isEmpty()) {
            return currentFamily;
        }
        return queues.keySet().stream().filter(f -> !queues.get(f).isEmpty()).findFirst().orElse(null);
    }

    List<String> getCargoList() {
        return new ArrayList<>(cargoList);
    }

    void dropForNaughtyFamily(String family) {
        naughtyFamilies.add(family);
    }
}
