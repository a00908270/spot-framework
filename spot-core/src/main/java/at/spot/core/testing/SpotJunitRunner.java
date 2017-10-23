package at.spot.core.testing;

import org.junit.runners.model.InitializationError;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import at.spot.core.infrastructure.support.spring.Registry;
import at.spot.core.support.util.ClassUtil;
import de.invesdwin.instrument.DynamicInstrumentationLoader;

public class SpotJunitRunner extends SpringJUnit4ClassRunner {

	static {
		// dynamically attach java agent to jvm if not already present
		DynamicInstrumentationLoader.waitForInitialized();
		// weave all classes before they are loaded as beans
		DynamicInstrumentationLoader.initLoadTimeWeavingContext();

		if (!InstrumentationLoadTimeWeaver.isInstrumentationAvailable()) {
			throw new IllegalStateException("Instrumentation not available!");
		} else {
			System.out.println("Instrumentation available!");
		}
	}

	protected IntegrationTest testAnnotation;

	public SpotJunitRunner(Class<?> clazz) throws InitializationError {
		super(clazz);

		testAnnotation = ClassUtil.getAnnotation(clazz, IntegrationTest.class);

		Registry.setMainClass(this.testAnnotation.initClass());
	}
}
