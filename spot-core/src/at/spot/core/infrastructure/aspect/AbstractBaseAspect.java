package at.spot.core.infrastructure.aspect;

import java.lang.annotation.Annotation;

import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import at.spot.core.infrastructure.service.TypeService;

@Configurable
public abstract class AbstractBaseAspect {

	@Autowired
	protected TypeService typeService;

	protected <A extends Annotation> A getAnnotation(JoinPoint joinPoint, Class<A> annotation) {
		return typeService.getAnnotation(joinPoint, annotation);
	}

	public void setTypeService(TypeService typeService) {
		this.typeService = typeService;
	}

	protected String createSignature(JoinPoint joinPoint) {
		return String.format("%s.%s", joinPoint.getSignature().getClass().getSimpleName(),
				joinPoint.getSignature().getName());
	}
}