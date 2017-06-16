package br.com.yacatecuhtli.domain.budget.group;

import br.com.yacatecuhtli.core.exception.ErrorMessageCode;
import lombok.Getter;

public enum BudgetGroupMessageCode implements ErrorMessageCode {

    BUDGET_GROUP_NAME_IS_BLANK("budget-group.name.error.is-blank"),
    BUDGET_GROUP_NAME_NOT_AVAILABLE("budget-group.name.error.not-available"),
    BUDGET_GROUP_DOES_NOT_EXISTS("budget-group.error.not-exists");

    @Getter
    private String messageKey;

    private BudgetGroupMessageCode(String messageKey) {
        this.messageKey = messageKey;
    }

}
