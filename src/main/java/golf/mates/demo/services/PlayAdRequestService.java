package golf.mates.demo.services;

import golf.mates.demo.repositories.PlayAdRequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PlayAdRequestService {
    private final PlayAdRequestRepository playAdRequestRepository;

}