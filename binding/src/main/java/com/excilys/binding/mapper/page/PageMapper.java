package com.excilys.binding.mapper.page;

import com.excilys.util.MyPage;
import org.springframework.data.domain.Page;


public class PageMapper {
    public static MyPage springPageToMyPage(Page page) {
        return new MyPage.Builder()
        .elements(page.getContent())
                .nbElementPage(page.getSize())
                .nbElementTotal(page.getTotalElements())
                .nbPage(page.getNumber())
                .build();

    }
}
