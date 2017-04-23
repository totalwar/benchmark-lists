package com.totalwar;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InsertAtBeginning5000Benchmark {

    private static final int INITIAL_SIZE = 5000;

    @State(Scope.Thread)
    public static class WastedCapacityArrayListState {

        public List<String> list;

        @Setup(Level.Invocation)
        public void doSetup() {
            list = new ArrayList<>(INITIAL_SIZE);
            for (int i = 0; i < INITIAL_SIZE; ++i) {
                list.add("string");
            }
        }
    }

    @State(Scope.Thread)
    public static class ReservedCapacityArrayListState {

        public List<String> list;

        @Setup(Level.Invocation)
        public void doSetup() {
            list = new ArrayList<>(2 * INITIAL_SIZE);
            for (int i = 0; i < INITIAL_SIZE; ++i) {
                list.add("string");
            }
        }
    }

    @State(Scope.Thread)
    public static class LinkedListState {

        public List<String> list;

        @Setup(Level.Invocation)
        public void doSetup() {
            list = new LinkedList<>();
            for (int i = 0; i < INITIAL_SIZE; ++i) {
                list.add("string");
            }
        }
    }

    @Benchmark
    public List<String> arrayListInsertAtBeginningWithoutInitialCapacity(WastedCapacityArrayListState state) {
        state.list.add(0, "string");
        return state.list;
    }

    @Benchmark
    public List<String> arrayListInsertAtBeginningWithInitialCapacity(ReservedCapacityArrayListState state) {
        state.list.add(0, "string");
        return state.list;
    }

    @Benchmark
    public List<String> linkedListInsertAtBeginning(LinkedListState state) {
        state.list.add(0, "string");
        return state.list;
    }
}
