package de.pruefbit.kata;

/**
 * ToyMachines give back presents on demand, as long as there are presents
 * to be produced, according to the machine setup.
 * ToyMachines work only on demand, but never self-triggered.
 * If set up accordingly (with a list of families and a production limit
 * per family), ToyMachines will not give new presents, when all limits for
 * all families got exceeded. Instead, "null" will be returned.
 * ToyMachines do not guarantee delivery of all packets for one family in a row.
 * The production of presents for multiple families may be interleaved.
 * ToyMachines always obey a ProductionPlan. If made without a ProductionPlan,
 * the machines will make their own default ProductionPlan.
 *
 * @see ProductionPlan
 */
class ToyMachine {
    Present givePresent() {
        return new Present();
    }
}
