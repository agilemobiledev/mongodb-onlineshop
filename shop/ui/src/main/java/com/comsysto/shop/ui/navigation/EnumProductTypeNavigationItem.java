package com.comsysto.shop.ui.navigation;

import com.comsysto.shop.repository.product.model.ProductType;

import java.lang.annotation.*;

/**
 * User: christian.kroemer@comsysto.com
 * Date: 6/21/13
 * Time: 4:29 PM
 */
@Documented
@Retention( RetentionPolicy.RUNTIME )
@Target( value = ElementType.TYPE )
public @interface EnumProductTypeNavigationItem {

    Class<? extends ProductType> enumClazz();
    String defaultEnum();
    String group() default "main";
    String visible() default "";
    int sortOrder();
}
