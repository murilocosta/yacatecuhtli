package br.com.yacatecuhtli.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.yacatecuhtli.core.AbstractTemplateLoader;
import br.com.yacatecuhtli.domain.account.Account;
import br.com.yacatecuhtli.domain.account.AccountJson;

public class AccountTemplateLoader extends AbstractTemplateLoader {

    public static final String VALID_ACCOUNT_TEMPLATE = "valid";

    @Override
    public void load() {
        Rule accountRule = new Rule();
        accountRule.add("name", accountRule.uniqueRandom(FAKER.lorem().words(30).toArray()));

        Fixture.of(Account.class).addTemplate(VALID_ACCOUNT_TEMPLATE, accountRule);
        Fixture.of(AccountJson.class).addTemplate(VALID_ACCOUNT_TEMPLATE, accountRule);
    }

}
