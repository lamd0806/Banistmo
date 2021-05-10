package co.com.andrea.bancolombia.tasks;

import co.com.andrea.bancolombia.models.Tasa;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.util.List;

import static co.com.andrea.bancolombia.userinterfaces.Tasas.*;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ConvertirTasa implements Task {

    private List<Tasa> datosTasa;

    public ConvertirTasa(List<Tasa> datosTasa) {
        this.datosTasa = datosTasa;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        Tasa t=datosTasa.get(0);
        actor.attemptsTo(
                WaitUntil.the(OPCION_NEGOCIOS, isVisible()).forNoMoreThan(20).seconds(),
                Click.on(OPCION_NEGOCIOS),
                Click.on(OPCION_HERRAMIENTAS),
                Click.on(OPCION_CONVERSOR_TASAS),
                //Click.on(CALCULAR_NOMINAL_EFECTIVA),
                Enter.theValue(t.getIngresaTasa()).into(INGRESAR_TASA),
                SelectFromOptions.byVisibleText(t.getPeriodicidadDeseada()).from(INGRESAR_PERIODICIDAD),
                SelectFromOptions.byVisibleText(t.getCapitalizacion()).from(INGRESAR_CAPITALIZACION)

        );
    }

    public static ConvertirTasa ConInformacionPrincipal(List<Tasa> tasa) {
        return Instrumented.instanceOf(ConvertirTasa.class).withProperties(tasa);
    }


}
