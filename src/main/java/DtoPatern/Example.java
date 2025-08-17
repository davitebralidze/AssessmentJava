package DtoPatern;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Example {
    @JsonProperty
    private final String subjectId;
    @JsonProperty
    private final String studyId;
    @JsonProperty
    private final PaymentStudySettingDto paymentStudySettingDto;

    private Example(Builder builder) {
        this.subjectId = builder.subjectId;
        this.studyId = builder.studyId;
        this.paymentStudySettingDto = builder.paymentStudySettingDto;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public String getStudyId() {
        return studyId;
    }

    public PaymentStudySettingDto getPaymentStudySettingDto() {
        return paymentStudySettingDto;
    }

    public static class Builder {
        private String subjectId;
        private String studyId;
        private PaymentStudySettingDto paymentStudySettingDto;

        public Builder withSubjectId(String subjectId) {
            this.subjectId = subjectId;
            return this;
        }

        public Builder withStudyId(String studyId) {
            this.studyId = studyId;
            return this;
        }

        public Builder withPaymentStudySettingDto(PaymentStudySettingDto paymentStudySettingDto) {
            this.paymentStudySettingDto = paymentStudySettingDto;
            return this;
        }

        public Example build() {
            return new Example(this);
        }
    }

    public static class PaymentStudySettingDto {
        @JsonProperty
        private final boolean paymentsEnabled;
        @JsonProperty
        private final boolean reimbursementsEnabled;
        @JsonProperty
        private final String contractedCurrency;

        private PaymentStudySettingDto(paymentStudySettingDtoBuilder builder) {
            this.paymentsEnabled = builder.paymentsEnabled;
            this.reimbursementsEnabled = builder.reimbursementsEnabled;
            this.contractedCurrency = builder.contractedCurrency;
        }

        public boolean isPaymentsEnabled() {
            return paymentsEnabled;
        }

        public boolean isReimbursementsEnabled() {
            return reimbursementsEnabled;
        }

        public String getContractedCurrency() {
            return contractedCurrency;
        }

        public static class paymentStudySettingDtoBuilder {
            private boolean paymentsEnabled;
            private boolean reimbursementsEnabled;
            private String contractedCurrency;

            public paymentStudySettingDtoBuilder withPaymentsEnabled(boolean paymentsEnabled) {
                this.paymentsEnabled = paymentsEnabled;
                return this;
            }

            public paymentStudySettingDtoBuilder withReimbursementsEnabled(boolean reimbursementsEnabled) {
                this.reimbursementsEnabled = reimbursementsEnabled;
                return this;
            }

            public paymentStudySettingDtoBuilder withContractedCurrency(String contractedCurrency) {
                this.contractedCurrency = contractedCurrency;
                return this;
            }

            public PaymentStudySettingDto build() {
                return new PaymentStudySettingDto(this);
            }
        }
    }
}