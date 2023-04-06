package golf.mates.demo.dtos.responses;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class AdReqResponseDto {

    private long playAdRequestId;
    private long playAdId;
    private long requesterId;
    private String requester;
    private double requesterHandicap;

    private String status;
    private String requestCreatedTime;


}
