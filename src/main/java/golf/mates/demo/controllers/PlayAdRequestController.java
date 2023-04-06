package golf.mates.demo.controllers;

import golf.mates.demo.dtos.request.PlayAdRequestDto;
import golf.mates.demo.dtos.request.RequestAdSlotDto;
import golf.mates.demo.dtos.responses.AdReqResponseDto;
import golf.mates.demo.entities.PlayAdRequest;
import golf.mates.demo.services.PlayAdRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/playadrequest")
@RequiredArgsConstructor
@Slf4j
public class PlayAdRequestController {
    private final PlayAdRequestService playAdRequestService;

    @PostMapping("")
    public ResponseEntity<HttpStatus> handlePlayAdRequest(@RequestBody RequestAdSlotDto requestAdSlotDto) {
        playAdRequestService.handleNewPlayAdRequest(requestAdSlotDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/to/{userId}")
    public ResponseEntity<List<AdReqResponseDto>> getAllRequestsToUser(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(playAdRequestService.findAllRequestByAdCreator(userId), HttpStatus.OK);
    }

    @GetMapping("/pending/to/{userId}")
    public ResponseEntity<List<AdReqResponseDto>> getAllPendingRequestsToUser(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(playAdRequestService.findAllRequestByAdCreatorStatusPending(userId), HttpStatus.OK);
    }

    @GetMapping("/by/{userId}")
    public ResponseEntity<List<AdReqResponseDto>> getAllRequestsByUser(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(playAdRequestService.findAllRequestByUser(userId), HttpStatus.OK);
    }

    @GetMapping("/on/{playAdId}")
    public ResponseEntity<List<AdReqResponseDto>> getAllRequestsOnPlayAd(@PathVariable("playAdId") Long playAdId) {
        return new ResponseEntity<>(playAdRequestService.findAllRequestByPlayAd(playAdId), HttpStatus.OK);
    }

    @PutMapping("/denied/{playAdRequestId}")
    public ResponseEntity<HttpStatus> handleAdRequestDeny(@PathVariable("playAdRequestId") Long playAdRequestId) {
        playAdRequestService.setPlayAdRequestDenied(playAdRequestId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/accepted/{playAdRequestId}")
    public ResponseEntity<HttpStatus> handleAdRequestAccept(@PathVariable("playAdRequestId") Long playAdRequestId) {
        playAdRequestService.setPlayAdRequestAccepted(playAdRequestId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}