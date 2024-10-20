package model;

import java.util.List;

public class TestData {

    private List<TestData.items> items;

    public List<TestData.items> getItems() {
        return items;
    }

    public static class items {
        private String name;
        private Integer quantity;

        public String getName() {
            return name;
        }

        public Integer getQuantity() {
            return quantity;
        }
    }
}
