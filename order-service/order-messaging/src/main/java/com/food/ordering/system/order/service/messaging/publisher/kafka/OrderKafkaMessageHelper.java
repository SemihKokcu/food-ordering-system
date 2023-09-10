package com.food.ordering.system.order.service.messaging.publisher.kafka;

import com.food.ordering.system.kafka.order.avro.model.PaymentRequestAvroModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Component
public class OrderKafkaMessageHelper {
    public <T> ListenableFutureCallback<SendResult<String, T>> getKafkaCallBack(String paymentResponseTopicName, T requestAvroModel,String orderId,String requestAvroModelName) {
        return  new ListenableFutureCallback<SendResult<String, T>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("Error while sending"+ requestAvroModelName +
                        "message: {} to topic {}",requestAvroModel.toString(),paymentResponseTopicName,ex);
            }

            @Override
            public void onSuccess(SendResult<String, T> result) {
                RecordMetadata metadata = result.getRecordMetadata();
                log.info("Received successfull response from Kafka for order id {}" +
                                "Topic: {} Partition: {} Offset: {} Timestamp: {}",
                        orderId,
                        metadata.topic(),
                        metadata.offset(),
                        metadata.timestamp());

            }
        };
    }
}
