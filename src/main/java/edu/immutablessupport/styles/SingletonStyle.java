package edu.immutablessupport.styles;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by KanthKumar on 3/5/17.
 */
@Target({ElementType.PACKAGE, ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
@Value.Style(
        visibility = ImplementationVisibility.PUBLIC,
        defaults = @Value.Immutable(singleton = true, builder = false))
public @interface SingletonStyle {
}
