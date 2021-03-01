package SimpleBoard.util;

import com.nhncorp.lucy.security.xss.LucyXssFilter;
import com.nhncorp.lucy.security.xss.XssSaxFilter;
import org.springframework.stereotype.Component;

@Component
public class XSSChecker {
    public String checkXSS(String dirty){
        LucyXssFilter xssFilter = XssSaxFilter.getInstance();
        String clean = xssFilter.doFilter(dirty);
        System.out.println(clean);
        return clean;
    }
}
