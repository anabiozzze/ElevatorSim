package anabiozzze.elevator.elevator.statemachine;

import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;


@org.springframework.context.annotation.Configuration
@EnableStateMachine
public class Configuration extends EnumStateMachineConfigurerAdapter<States, Events> {
    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states)
            throws Exception {
        states
                .withStates()
                .initial(States.STAY)
                .states(EnumSet.allOf(States.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
            throws Exception {
        transitions
                .withExternal()
                .source(States.STAY).target(States.CLOSED_DOOR)
                .event(Events.CLOSE_DOOR)

                .and()

                .withExternal()
                .source(States.CLOSED_DOOR).target(States.WAITING)
                .event(Events.WAIT)

                .and()

                .withExternal()
                .source(States.WAITING).target(States.GOING)
                .event(Events.MOVE)

                .and()

                .withExternal()
                .source(States.GOING).target(States.STAY)
                .event(Events.STAY)

                .and()

                .withExternal()
                .source(States.STAY).target(States.WAITING)
                .event(Events.WAIT)

                .and()

                .withExternal()
                .source(States.WAITING).target(States.OPEN_DOOR)
                .event(Events.OPEN_DOOR)

                .and()

                .withExternal()
                .source(States.OPEN_DOOR).target(States.WAITING_PASSAGERS)
                .event(Events.WAIT_PASSAGERS);


    }
}

