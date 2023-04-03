package golf.mates.demo.controllers;

import golf.mates.demo.services.PlayAdRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/playadrequest")
@RequiredArgsConstructor
@Slf4j
public class PlayAdRequestController {
    private final PlayAdRequestService playAdRequestService;

}