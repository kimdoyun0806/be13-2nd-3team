package com.beyond3.yyGang.nsupplement.service;

import com.beyond3.yyGang.handler.exception.UserException;
import com.beyond3.yyGang.handler.message.UserExceptionMessage;
import com.beyond3.yyGang.hfunction.*;
import com.beyond3.yyGang.ingredient.*;
import com.beyond3.yyGang.nsupplement.NSupplement;
import com.beyond3.yyGang.nsupplement.dto.NSupplementModifyDto;
import com.beyond3.yyGang.nsupplement.repository.NSupplementRepository;
import com.beyond3.yyGang.nsupplement.dto.NSupplementRegisterDto;
import com.beyond3.yyGang.user.domain.Role_name;
import com.beyond3.yyGang.user.domain.User;
import com.beyond3.yyGang.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NSupplementService {

    private final UserRepository userRepository;
    private final NSupplementRepository nsupplementRepository;
    private final IngredientRepository ingredientRepository;
    private final HFunctionalRepository hFunctionalRepository;
    private final IngredientCategoryRepository ingredientCategoryRepository;
    private final HFunctionalCategoryRepository hFunctionalCategoryRepository;

    @Transactional
    public List<NSupplementRegisterDto> getAllNSupplements(String email) {

        // user 역할 확인
        User user = extractedUser(email);

        // 등록한 상품 리스트 확인
        List<NSupplement> products = nsupplementRepository.findBySeller(user);

//        // 등록한 상품이 없으면 예외 던져버려 -> 아니면 빈 페이지 보여줄까?
//        if(products.isEmpty()){
//            throw new UserException(UserExceptionMessage.NO_PRODUCTS_FOR_SELLER);
//        }

        List<NSupplementRegisterDto> result = new ArrayList<>();

        for (NSupplement product : products) {
            NSupplementRegisterDto nSupplementRegisterDto = new NSupplementRegisterDto(product);

            List<IngredientName> ingredients = ingredientCategoryRepository.findBynSupplement(product)
                    .stream().map(n -> n.getIngredient().getIngredientName())
                    .toList();

            List<HFunctionName> hFunctionNames = hFunctionalCategoryRepository.findBynSupplement(product)
                    .stream().map(n -> n.getHFunctionalItem().getHealthName())
                    .toList();

            nSupplementRegisterDto.setIngredients(ingredients);
            nSupplementRegisterDto.setHFunctionalItems(hFunctionNames);

            result.add(nSupplementRegisterDto);
        }

        return result;
    }

    @Transactional
    // 상품 등록
    public void registerProduct(String email, NSupplementRegisterDto nSupplementRegisterDto) {

        // 역할이 SELLER인지 확인
        User seller = extractedUser(email);

        // 상품 먼저 등록 -> 판매자 이름으로 등록
        NSupplement nSupplement = nSupplementRegisterDto.toEntity(seller);
        NSupplement savedProduct = nsupplementRepository.save(nSupplement);

        // 상품에 등록된 성분 카테고리에 관련된 내용 입력
        saveIngredientCategory(nSupplementRegisterDto.getIngredients(), savedProduct);
        // 상품에 등록된 건강 카테고리에 관련된 내용 입력
        saveHFunctionalCategory(nSupplementRegisterDto.getHFunctionalItems(), savedProduct);
    }

    @Transactional
    // 상품 삭제 서비스 로직
    public void deleteProduct(String email, Long nSupplementId) {
        // 사용자 확인
        User seller = extractedUser(email);

        // Seller가 nSupplementId의 상품을 등록했다면 해당 상품 가져오기
        NSupplement product = validateSeller(seller, nSupplementId);

        // 상품 삭제의 경우 -> ingredientCategory, HFunctioncalCategory에서 각각 제거가 먼저 되어야 함
        deleteIngredientCategory(product);
        deleteHFunctionalCategory(product);

        // 이후 NSupplement에서 삭제 가능
        nsupplementRepository.deleteById(nSupplementId);
    }

    @Transactional
    // 상품 수정 서비스 로직
    public void modifyProduct(String email, Long nSupplementId, NSupplementModifyDto dto) {
        User seller = extractedUser(email);

        // Seller가 nSupplementId의 상품을 등록했다면 해당 상품 가져오기
        NSupplement product = validateSeller(seller, nSupplementId);

        product.updateNSupplement(dto);

        // Category에 있는 애들 먼저 지우고
        deleteIngredientCategory(product);
        deleteHFunctionalCategory(product);

        // 새로 입력된 상품 수정 카테고리를 다시 입력하기
        saveIngredientCategory(dto.getIngredients(), product);
        saveHFunctionalCategory(dto.getHFunctionalItems(), product);
    }

    // IngredientCategory에서 특정 상품 삭제
    private void deleteIngredientCategory(NSupplement product) {
        List<IngredientCategory> inCategoryList = ingredientCategoryRepository.findBynSupplement(product);
        ingredientCategoryRepository.deleteAll(inCategoryList);
    }

    // HFunctinoalCategory에서 특정 상품 삭제
    private void deleteHFunctionalCategory(NSupplement product) {
        List<HFunctionalCategory> hfuncCategoryList = hFunctionalCategoryRepository.findBynSupplement(product);
        hFunctionalCategoryRepository.deleteAll(hfuncCategoryList);
    }

    // ingredientCategoryRepository에 성분 태그 저장
    private void saveIngredientCategory(List<IngredientName> ingredientNames, NSupplement product) {
        // 상품에 등록된 성분 카테고리에 관련된 내용 입력
        for (IngredientName ingredientName : ingredientNames) {

            Ingredient ingredient = ingredientRepository.findByingredientName(ingredientName)
                    .orElseThrow(() -> new UserException(UserExceptionMessage.INGREDIENT_NOT_FOUND));

            IngredientCategory ingredientCategory = new IngredientCategory(product, ingredient);
            ingredientCategoryRepository.save(ingredientCategory);
        }
    }

    // hFunctionalCategoryRepository에 건강 기능 태그 저장
    private void saveHFunctionalCategory(List<HFunctionName> hFunctionNames, NSupplement product) {
        // 상품에 등록된 성분 카테고리에 관련된 내용 입력
        for (HFunctionName hFunctionalItem : hFunctionNames) {

            HFunctionalItem hitem = hFunctionalRepository.findByHealthName(hFunctionalItem)
                    .orElseThrow(() -> new UserException(UserExceptionMessage.HEALTH_FUNCTIONAL_ITEM_NOT_FOUND));

            HFunctionalCategory hFunctionalCategory = new HFunctionalCategory(product, hitem);
            hFunctionalCategoryRepository.save(hFunctionalCategory);
        }
    }

    // Seller가 가지고 있는 상품 리스트 안에 해당 상품이 있는지 확인
    private NSupplement validateSeller(User seller, Long nSupplementId){
        // Seller가 등록한 상품들 조회
        List<NSupplement> productList = nsupplementRepository.findBySeller(seller);

        // nSupplementId 아이디의 상품이 존재하는지 확인
        NSupplement product = nsupplementRepository.findByproductId(nSupplementId)
                .orElseThrow(() -> new UserException(UserExceptionMessage.CANNOT_FOUND_PRODUCTS));

        // 삭제할 상품이 없거나 삭제할 상품 아이디가 SELLER가 등록한 상품이 아닌 경우 -> 예외 던지게
        if (productList.isEmpty() || !productList.contains(product)) {
            throw new UserException(UserExceptionMessage.NO_REGISTERED_PRODUCTS);
        }
        return product;
    }

    // 사용자 추출 -> 역할이 SELLER일 때만
    private User extractedUser(String email) {
        // 사용자 확인
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(UserExceptionMessage.USER_NOT_FOUND));

        // user의 역할이 SELLER인 경우만 해당 작업 가능
        if (!user.getRole().equals(Role_name.SELLER)) {
            throw new UserException(UserExceptionMessage.ONLY_SELLER_CAN_ACCESS);
        }
        return user;
    }
}