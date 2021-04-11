package com.gustavoms.messagescheduling.data;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class QueryManipulatorTest {

    @Test
    public void whenWhereOrAndWithEmptyBuilderThenWhere() {
        assertThat(QueryManipulator.whereOrAnd(new StringBuilder())).isEqualTo("WHERE ");
    }

    @Test
    public void whenWhereOrAndWithFilledBuilderThenAnd() {
        assertThat(QueryManipulator.whereOrAnd(new StringBuilder("SELECT WHERE "))).isEqualTo("AND ");
    }

    @Test
    public void whenCheckValidOrderWithValidOrderThenTrue() {
        assertThat(QueryManipulator.isValidOrder("order", Set.of("order"))).isTrue();
    }

    @Test
    public void whenCheckValidOrderWithValidDescOrderThenTrue() {
        assertThat(QueryManipulator.isValidOrder("-order", Set.of("order"))).isTrue();
    }

    @Test
    public void whenCheckValidOrderWithInvalidOrderThenFalse() {
        assertThat(QueryManipulator.isValidOrder("invalid", Set.of("order"))).isFalse();
    }

    @Test
    public void whenCheckValidOrderWithOnlyDescPrefixThenFalse() {
        assertThat(QueryManipulator.isValidOrder("-", Set.of("order"))).isFalse();
    }

    @Test
    public void whenSimpleOrderThenAsc() {
        assertThat(QueryManipulator.ascOrDesc("order")).isEqualTo("ASC");
    }

    @Test
    public void whenOrderWithPrefixThenDesc() {
        assertThat(QueryManipulator.ascOrDesc("-order")).isEqualTo("DESC");
    }

    @Test
    public void whenExtractOrderThenReturnOrder() {
        assertThat(QueryManipulator.extractOrder("order")).isEqualTo("order");
    }

    @Test
    public void whenExtractOrderWithPrefixThenReturnOrder() {
        assertThat(QueryManipulator.extractOrder("-order")).isEqualTo("order");
    }
}
