package com.beyond3.yyGang.nsupplement.repository;

import com.beyond3.yyGang.nsupplement.dto.NSupplementResponseDtoV2;
import com.beyond3.yyGang.nsupplement.dto.NSupplementSearchRequestDtoV2;
import com.beyond3.yyGang.nsupplement.dto.PageResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NSupplementRepositoryCustom {

    PageResponseDto<NSupplementResponseDtoV2> searchPageV2(NSupplementSearchRequestDtoV2 searchRequest, Pageable pageable, SortType sortType);
}