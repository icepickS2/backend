package app.annotaion;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Documented
@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "idx")
@NoArgsConstructor
@AllArgsConstructor
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface BaseEntity {
}