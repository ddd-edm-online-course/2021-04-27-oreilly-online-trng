package com.mattstine.dddworkshop.pizzashop.suites;

import com.mattstine.dddworkshop.pizzashop.kitchen.*;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

/**
 * @author Matt Stine
 */
@RunWith(JUnitPlatform.class)
@SelectClasses({
		KitchenOrderTests.class,
		PizzaTests.class,
		InProcessEventSourcedKitchenOrderRepositoryTests.class,
		InProcessEventSourcedPizzaRepositoryTests.class,
		DefaultKitchenServiceTests.class,
		DefaultKitchenServiceIntegrationTests.class
})
@IncludeTags({
		"Lab1Tests",
		"Lab2Tests",
		"Lab3Tests",
		"Lab4Tests",
		"Lab5Tests",
		"Lab6Tests"
})
public class Lab6Suite {
}
