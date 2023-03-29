package golf.mates.demo.utils;

import golf.mates.demo.security.SecurityUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

public class Utils {

    public static class UserUtils {
        private UserUtils() {
        }
        public static Long getLoggedInUserId() {
            Authentication authentication =
                    SecurityContextHolder.getContext().getAuthentication();
            SecurityUser loggedInUser = (SecurityUser) authentication.getPrincipal();
            return loggedInUser.getUserId();
        }

        public static String getLoggedInUsername() {
            Authentication authentication =
                    SecurityContextHolder.getContext().getAuthentication();
            return authentication.getName();
        }


    }


}
