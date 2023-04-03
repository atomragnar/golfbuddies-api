package golf.mates.demo.services;


import golf.mates.demo.entities.PlayAdSlot;
import golf.mates.demo.repositories.PlayAdSlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;



@RequiredArgsConstructor
@Service
public class PlayAdSlotService {

    private final PlayAdSlotRepository playAdSlotRepository;


    public List<PlayAdSlot> findBookedSlotsByUserId(Long userId) {
        List<PlayAdSlot> usersPlayAdSlots = playAdSlotRepository.findByPlayer_Id(userId);
        if (!usersPlayAdSlots.isEmpty()) {
            return usersPlayAdSlots;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
