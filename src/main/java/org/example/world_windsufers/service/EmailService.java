package org.example.world_windsufers.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.world_windsufers.model.Forecast;
import org.example.world_windsufers.model.Weather;
import org.example.world_windsufers.properties.EmailProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    private final EmailProperties email;

    @Async("asyncTaskExecutor")
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom(email.getUsername());
        mailSender.send(message);
    }

    public String createEmailBody(Weather location, Forecast bestForecast) {
        return MessageFormat.format(
                "The request for finding the best windsurfing location was made on: {0}\n" +
                        "The best Localization: {1}\n" +
                        "Wind speed: {2} m/s\n" +
                        "Temperature: {3} °C\n" +
                        "Score: {4}",
                LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                location.getCity_name(),
                bestForecast.getWind_spd(),
                bestForecast.getTemp(),
                bestForecast.getScore().doubleValue()
        );
    }
}
