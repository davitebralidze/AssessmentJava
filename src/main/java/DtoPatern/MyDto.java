package DtoPatern;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MyDto {
    @JsonProperty
    private final int id;
    @JsonProperty
    private final Details details;

    private MyDto(Builder builder) {
        this.id = builder.id;
        this.details = builder.details;
    }

    public static class Builder {
        private int id;
        private Details details;

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withDetails(Details details) {
            this.details = details;
            return this;
        }

        public MyDto build() {
            return new MyDto(this);
        }
    }

    // ======= NESTED CLASSES =======

    public static class Details {
        @JsonProperty
        public final int age;
        @JsonProperty
        public final String email;

        private Details(Builder builder) {
            this.age = builder.age;
            this.email = builder.email;
        }

        public static class Builder {
            private int age;
            private String email;

            public Builder withAge(int age) {
                this.age = age;
                return this;
            }

            public Builder withEmail(String email) {
                this.email = email;
                return this;
            }

            public Details build() {
                return new Details(this);
            }
        }
    }
}

