package com.beyond3.yyGang.nsupplement.repository;

import com.beyond3.yyGang.nsupplement.dto.NSupplementResponseDto;

import java.awt.print.Pageable;
import java.util.List;

public interface NSupplementRepositoryCustom {

    List<NSupplementResponseDto> search(Pageable pageable);

}