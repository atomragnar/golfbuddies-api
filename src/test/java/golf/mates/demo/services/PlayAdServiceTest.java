/*
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import golf.mates.demo.entities.PlayAd;
import golf.mates.demo.entities.PlayAdRequest;
import golf.mates.demo.entities.PlayAdSlot;
import golf.mates.demo.entities.User;
import golf.mates.demo.exceptions.PlayAdNotFoundException;
import golf.mates.demo.exceptions.PlayAdRequestNotFoundException;
import golf.mates.demo.repositories.PlayAdRepository;
import golf.mates.demo.repositories.PlayAdRequestRepository;

@ExtendWith(MockitoExtension.class)
public class PlayAdServiceTest {

    @Mock
    private PlayAdRepository playAdRepository;

    @Mock
    private PlayAdRequestRepository playAdRequestRepository;

    @InjectMocks
    private PlayAdService playAdService;

    private User user1;
    private User user2;
    private User user3;
    private PlayAd playAd;
    private List<PlayAdRequest> requests;

    @BeforeEach
    void setUp() {
        user1 = new User("user1");
        user2 = new User("user2");
        user3 = new User("user3");

        playAd = new PlayAd(user1);

        PlayAdSlot slot1 = new PlayAdSlot(playAd, 1);
        PlayAdSlot slot2 = new PlayAdSlot(playAd, 2);
        PlayAdSlot slot3 = new PlayAdSlot(playAd, 3);

        playAd.getSlots().add(slot1);
        playAd.getSlots().add(slot2);
        playAd.getSlots().add(slot3);

        PlayAdRequest request1 = new PlayAdRequest();
        request1.setRequester(user2);
        request1.setPlayAd(playAd);
        request1.setSlotIndex(1);

        PlayAdRequest request2 = new PlayAdRequest();
        request2.setRequester(user3);
        request2.setPlayAd(playAd);
        request2.setSlotIndex(2);

        requests = new ArrayList<>();
        requests.add(request1);
        requests.add(request2);
    }

    @Test
    void testCreatePlayAd() {
        when(playAdRepository.save(any())).thenReturn(playAd);

        PlayAd result = playAdService.createPlayAd(user1);

        assertEquals(playAd, result);
    }

    @Test
    void testGetPlayAdById() {
        when(playAdRepository.findById(anyLong())).thenReturn(Optional.of(playAd));

        PlayAd result = playAdService.getPlayAdById(1L);

        assertEquals(playAd, result);
    }

    @Test
    void testGetPlayAdByIdThrowsPlayAdNotFoundException() {
        when(playAdRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(PlayAdNotFoundException.class, () -> {
            playAdService.getPlayAdById(1L);
        });
    }

    @Test
    void testAddPlayerToPlayAd() {
        when(playAdRepository.findById(anyLong())).thenReturn(Optional.of(playAd));
        when(playAdRequestRepository.findByPlayAdAndSlotIndex(any(), anyInt())).thenReturn(Optional.empty());

        playAdService.addPlayerToPlayAd(1L, user2, 1);

        assertTrue(playAd.getSlots().get(0).getPlayer().equals(user2));
    }

    @Test
    void testCreatePlayAd() {
        User creator = new User("testuser", "password", "Test", "User");
        PlayAd playAd = new PlayAd(creator);
        when(playAdRepository.save(any(PlayAd.class))).thenReturn(playAd);

        PlayAd createdPlayAd = playAdService.createPlayAd(creator);

        assertNotNull(createdPlayAd.getId());
        assertEquals(creator, createdPlayAd.getCreator());
        assertEquals(3, createdPlayAd.getSlots().size());
    }

    @Test
    void testGetPlayAd() {
        Long playAdId = 1L;
        PlayAd playAd = new PlayAd();
        when(playAdRepository.findById(playAdId)).thenReturn(Optional.of(playAd));

        PlayAd retrievedPlayAd = playAdService.getPlayAd(playAdId);

        assertEquals(playAd, retrievedPlayAd);
    }

    @Test
    void testAddPlayerToPlayAd() {
        User creator = new User("testuser", "password", "Test", "User");
        User player = new User("testuser2", "password", "Test", "User2");
        PlayAd playAd = new PlayAd(creator);
        playAd.addPlayer(creator);
        when(userRepository.findByUsernameIgnoreCase(player.getUsername())).thenReturn(Optional.of(player));
        when(playAdRepository.findById(playAd.getId())).thenReturn(Optional.of(playAd));

        playAdService.addPlayerToPlayAd(playAd.getId(), player.getUsername());

        assertEquals(player, playAd.getSlots().get(1).getPlayer());
    }

    @Test
    void testRemovePlayerFromPlayAd() {
        User creator = new User("testuser", "password", "Test", "User");
        User player = new User("testuser2", "password", "Test", "User2");
        PlayAd playAd = new PlayAd(creator);
        PlayAdSlot slot = playAd.getSlots().get(0);
        slot.setPlayer(player);
        when(userRepository.findByUsernameIgnoreCase(player.getUsername())).thenReturn(Optional.of(player));
        when(playAdRepository.findById(playAd.getId())).thenReturn(Optional.of(playAd));

        playAdService.removePlayerFromPlayAd(playAd.getId(), player.getUsername());

        assertNull(slot.getPlayer());
    }

    @Test
    void testRequestToJoinPlayAd() {
        User requester = new User("testuser2", "password", "Test", "User2");
        User creator = new User("testuser", "password", "Test", "User");
        PlayAd playAd = new PlayAd(creator);
        when(userRepository.findByUsernameIgnoreCase(requester.getUsername())).thenReturn(Optional.of(requester));
        when(playAdRepository.findById(playAd.getId())).thenReturn(Optional.of(playAd));
        when(playAdRequestRepository.save(any(PlayAdRequest.class))).thenAnswer(i -> i.getArguments()[0]);

        PlayAdRequest request = playAdService.requestToJoinPlayAd(playAd.getId(), requester.getUsername());

        assertNotNull(request.getId());
        assertEquals(requester, request.getRequester());
        assertEquals(playAd, request.getPlayAd());
        assertEquals(0, request.getSlotIndex());
        assertEquals(PlayAdRequestStatus.PENDING, request.getStatus());
    }

*/
