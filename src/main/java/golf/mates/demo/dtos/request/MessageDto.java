package golf.mates.demo.dtos.request;

public record MessageDto(long senderId, long receiverId, String message) {
}
