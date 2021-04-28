package com.mattstine.dddworkshop.pizzashop.delivery;

import com.mattstine.dddworkshop.pizzashop.infrastructure.events.ports.EventLog;
import com.mattstine.dddworkshop.pizzashop.infrastructure.events.ports.Topic;
import com.mattstine.dddworkshop.pizzashop.infrastructure.repository.adapters.InProcessEventSourcedRepository;
import com.mattstine.dddworkshop.pizzashop.kitchen.KitchenOrderRef;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Matt Stine
 */
final class InProcessEventSourcedDeliveryOrderRepository extends InProcessEventSourcedRepository<DeliveryOrderRef, DeliveryOrder, DeliveryOrder.OrderState, DeliveryOrderEvent, DeliveryOrderAddedEvent> implements DeliveryOrderRepository {

	private Map<KitchenOrderRef, DeliveryOrderRef> kitchenOrderRefDeliveryOrderRefMap;

	InProcessEventSourcedDeliveryOrderRepository(EventLog eventLog, Topic topic) {
		super(eventLog,
				DeliveryOrderRef.class,
				DeliveryOrder.class,
				DeliveryOrder.OrderState.class,
				DeliveryOrderAddedEvent.class,
				topic);

		kitchenOrderRefDeliveryOrderRefMap = new HashMap<>();

		eventLog.subscribe(topic, e -> {
			if (e instanceof DeliveryOrderAddedEvent) {
				kitchenOrderRefDeliveryOrderRefMap.put(((DeliveryOrderAddedEvent) e).getState().getKitchenOrderRef(), ((DeliveryOrderAddedEvent) e).getRef());
			}
		});
	}

	@Override
	public DeliveryOrder findByKitchenOrderRef(KitchenOrderRef kitchenOrderRef) {
		DeliveryOrderRef ref = kitchenOrderRefDeliveryOrderRefMap.get(kitchenOrderRef);
		if (ref != null) {
			return this.findByRef(ref);
		}
		return null;
	}
}
