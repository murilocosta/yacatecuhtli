package br.com.yacatecuhtli.domain.budget.group;

import br.com.yacatecuhtli.template.BudgetGroupTemplateLoader;
import com.github.javafaker.Faker;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BudgetGroupServiceTests extends AbstractBudgetGroupServiceTests {

    @Test
    public void shouldListBudgetGroups() {
        int budgetGroupQuantity = 10;
        List<BudgetGroup> budgetGroupList = createPersistedObjectList(BudgetGroup.class, BudgetGroupTemplateLoader.VALID_BUDGET_GROUP_TEMPLATE, budgetGroupQuantity);
        List<BudgetGroupJson> resultSet = budgetGroupService.findAll(new PageRequest(0, 10)).getResult();
        Assert.assertThat(resultSet.size(), Matchers.equalTo(budgetGroupQuantity));
        for (BudgetGroup budgetGroup : budgetGroupList) {
            resultSet.stream()
                    .filter(result -> budgetGroup.getId().compareTo(result.getId()) == 0)
                    .forEach(result -> Assert.assertThat(result, Matchers.samePropertyValuesAs(budgetGroup.toJson())));
        }
    }

    @Test
    public void shouldFindBudgetGroupById() {
        BudgetGroup savedBudgetGroup = createPersistedObject(BudgetGroup.class, BudgetGroupTemplateLoader.VALID_BUDGET_GROUP_TEMPLATE);
        int savedBudgetGroupId = savedBudgetGroup.getId();
        BudgetGroupJson result = budgetGroupService.findOne(savedBudgetGroupId);
        Assert.assertThat(result.getId(), Matchers.equalTo(savedBudgetGroupId));
        Assert.assertThat(result, Matchers.samePropertyValuesAs(savedBudgetGroup.toJson()));
    }

    @Test
    public void shouldSaveBudgetGroup() {
        BudgetGroupJson payload = createObject(BudgetGroupJson.class, BudgetGroupTemplateLoader.VALID_BUDGET_GROUP_TEMPLATE);
        Assert.assertThat(payload.getId(), Matchers.nullValue());
        BudgetGroupJson savedBudgetGroup = budgetGroupService.save(payload);
        Assert.assertThat(savedBudgetGroup, Matchers.notNullValue());
        Assert.assertThat(savedBudgetGroup.getId(), Matchers.notNullValue());
    }

    @Test
    public void shouldUpdateBudgetGroup() {
        BudgetGroupJson payload = createPersistedObject(BudgetGroup.class, BudgetGroupTemplateLoader.VALID_BUDGET_GROUP_TEMPLATE).toJson();
        String oldName = payload.getName();
        payload.setName(new Faker().lorem().characters(15));
        budgetGroupService.update(payload.getId(), payload);
        BudgetGroup updatedBudgetGroup = getObject(BudgetGroup.class, payload.getId());
        Assert.assertThat(oldName, Matchers.not(Matchers.equalToIgnoringCase(updatedBudgetGroup.getName())));
    }

    public void shouldDeleteBudgetGroup() {
        Integer budgetGroupId = createPersistedObject(BudgetGroup.class, BudgetGroupTemplateLoader.VALID_BUDGET_GROUP_TEMPLATE).getId();
        budgetGroupService.destroy(budgetGroupId);
        BudgetGroup deleted = getObject(BudgetGroup.class, budgetGroupId);
        Assert.assertThat(deleted, Matchers.nullValue());
    }

}
