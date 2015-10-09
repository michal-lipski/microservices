package commands;

import com.example.CustomerRepository
import org.crsh.cli.Argument;
import org.crsh.cli.Command
import org.crsh.cli.Required;
import org.crsh.cli.Usage;
import org.crsh.command.BaseCommand
import org.crsh.command.InvocationContext;
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.actuate.endpoint.BeansEndpoint;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Custom commands
 */
@Usage("Hello")
public class hello {


    @Command
    @Usage("Say Hello")
    public String test(InvocationContext context, @Required @Argument arg) {
        def bean = context.attributes['spring.beanfactory'].getBean(CustomerRepository.class)
            def var = (CustomerRepository)(bean);
            return var.findAll()+arg;

    }
}
