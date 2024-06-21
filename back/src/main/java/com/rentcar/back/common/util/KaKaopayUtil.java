package com.rentcar.back.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.rentcar.back.common.object.KakaoReady;
import com.rentcar.back.dto.request.reservation.PostReservationRequestDto;

@Component
public class KaKaopayUtil {

    @Value("${kakao-pay.ready-url}") private String kakaoPayReadyUrl;
    @Value("${kakao-pay.key}") private String kakaoPayKey;
    @Value("${kakao-pay.approval-url}") private String kakaoPayApprovalUrl;
    @Value("${kakao-pay.cancel-url}") private String kakaoPayCancelUrl;
    
    public KakaoReady prepareKakaoPayment(PostReservationRequestDto dto, int reservationCode) {

        String orderId = UUID.randomUUID().toString();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", kakaoPayKey);
        headers.set("Content-Type", "application/json");

        Map<String, String> payParams = new HashMap<String, String>();

        String totalAmount = dto.getPrice().toString();
        Integer vatAmount =  dto.getPrice() / 11;

        payParams.put("cid", "TC0ONETIME");
        payParams.put("partner_order_id", orderId);
        payParams.put("partner_user_id", "차탕갑서");
        payParams.put("item_name", "차탕갑서 예약");
        payParams.put("quantity", "1");
        payParams.put("total_amount", totalAmount);
        payParams.put("vat_amount", vatAmount.toString());
        payParams.put("tax_free_amount", "0");
        payParams.put("approval_url", kakaoPayApprovalUrl);
        payParams.put("cancel_url", kakaoPayCancelUrl);
        payParams.put("fail_url", kakaoPayCancelUrl + reservationCode);

        HttpEntity<Map> request = new HttpEntity<>(payParams, headers);

        RestTemplate template = new RestTemplate();

        return template.postForObject(kakaoPayReadyUrl, request, KakaoReady.class);
    }

}
