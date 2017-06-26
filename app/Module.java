import actors.ApiActor;
import com.google.inject.AbstractModule;
import models.DataStore;
import play.libs.akka.AkkaGuiceSupport;


/**
 * Created by jerem on 6/24/2017.
 */
public class Module extends AbstractModule implements AkkaGuiceSupport {

    @Override
    protected void configure() {
        bind(DataStore.class).asEagerSingleton();
        bindActor(ApiActor.class, "Api-Actor");
    }
}
