package de.pruefbit.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductionPlanTest {
    private static final String FAMILY = "Miller";
    private static final String UNKNOWN_FAMILY = "Unknown";
    private static final List<String> LIST_OF_FAMILIES = Arrays.asList("Kojak", "McFly", FAMILY);
    private static final int GENERIC_PRODUCTION_LIMIT = 3;
    private static final int FAMILY_PRODUCTION_LIMIT = 3;
    private ProductionPlan productionPlan;

    @BeforeEach
    void setupEach() {
        productionPlan = new ProductionPlan();
    }

    @Test
    void accepts_a_family_to_be_added_to_its_family_list() {
        productionPlan.addFamily(FAMILY);
        assertEquals(Collections.singletonList(FAMILY), productionPlan.getFamilies());
    }

    @Test
    void returns_a_families_production_limit_on_request() {
        productionPlan.addFamily(FAMILY);
        assertEquals(1, productionPlan.getProductionLimit(FAMILY));
    }

    @Test
    void returns_zero_on_unknown_family() {
        productionPlan.addFamily(FAMILY);
        assertEquals(0, productionPlan.getProductionLimit(UNKNOWN_FAMILY));
    }

    @Test
    void increments_limit_by_family_addition() {
        productionPlan.addFamily(FAMILY);
        productionPlan.addFamily(FAMILY);
        assertEquals(2, productionPlan.getProductionLimit(FAMILY));
    }

    @Test
    void accept_addition_of_multiple_families_at_once() {
        productionPlan.addAllFamilies(LIST_OF_FAMILIES);
        LIST_OF_FAMILIES.forEach(f -> assertEquals(1, productionPlan.getProductionLimit(f)));
    }

    @Test
    void multiple_families_increase_limit_as_well() {
        productionPlan.addAllFamilies(LIST_OF_FAMILIES);
        productionPlan.addAllFamilies(LIST_OF_FAMILIES);
        LIST_OF_FAMILIES.forEach(f -> assertEquals(2, productionPlan.getProductionLimit(f)));
        productionPlan.addFamily(FAMILY);
        assertEquals(3, productionPlan.getProductionLimit(FAMILY));
    }

    @Test
    void accepts_global_production_limit_without_families() {
        productionPlan.setProductionLimit(GENERIC_PRODUCTION_LIMIT);
        assertEquals(GENERIC_PRODUCTION_LIMIT, productionPlan.getProductionLimit());
    }

    @Test
    void production_limit_can_be_set_for_specific_family() {
        productionPlan.addFamily(FAMILY);
        assertEquals(1, productionPlan.getProductionLimit(FAMILY));
        productionPlan.setProductionLimit(FAMILY, FAMILY_PRODUCTION_LIMIT);
        assertEquals(FAMILY_PRODUCTION_LIMIT, productionPlan.getProductionLimit(FAMILY));
    }
}