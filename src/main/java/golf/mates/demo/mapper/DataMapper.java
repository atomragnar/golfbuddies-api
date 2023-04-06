package golf.mates.demo.mapper;

import golf.mates.demo.dtos.responses.GolfClubResponseDto;
import golf.mates.demo.dtos.responses.GolfCourseDto;
import golf.mates.demo.dtos.responses.LocationDto;
import golf.mates.demo.entities.GolfClub;
import golf.mates.demo.entities.GolfCourse;
import golf.mates.demo.entities.Location;

import java.util.List;
import java.util.stream.Collectors;

public class DataMapper {




    public static GolfClubResponseDto golfClubToDto(GolfClub golfClub) {
        return new GolfClubResponseDto(
                golfClub.getId(),
                golfClub.getClub(),
                golfClub.getLocation().getId()
        );
    }

    public static List<GolfClubResponseDto> golfClubListToResponseDtoList(List<GolfClub> golfClubList) {
        return golfClubList.stream().map(DataMapper::golfClubToDto)
                .collect(Collectors.toList());
    }


    public static GolfCourseDto golfCourseToDto(GolfCourse golfCourse) {
        return new GolfCourseDto(
                golfCourse.getId(),
                golfCourse.getCourse(),
                golfCourse.getLocation().getId(),
                golfCourse.getGolfClub().getId()
        );
    }

    public static List<GolfCourseDto> golfCourseListToDtoList(List<GolfCourse> golfCourseList) {
        return golfCourseList.stream().map(DataMapper::golfCourseToDto)
                .collect(Collectors.toList());
    }


    public static LocationDto locationToDto(Location location) {
        return new LocationDto(
                location.getId(),
                location.getDistrict()
        );
    }


    public static List<LocationDto> locationListToDtoList(List<Location> locationList) {
        return locationList.stream().map(DataMapper::locationToDto)
                .collect(Collectors.toList());
    }


}
