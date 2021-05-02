package uz.pdp.appnewssite.aop;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckPermission {

    // Permissionni huquqlarini ifodalsh uchun foydalaniladi
    String values();
}
