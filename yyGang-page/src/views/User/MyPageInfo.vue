<template>
    <div class="info-container">
    <h5 class="fw-bold mb-4">기본정보</h5>

    <!-- 이름 -->
    <div class="form-group mb-3">
        <label class="form-label">이름</label>
        <input type="text" class="form-control" v-model="user.name" />
    </div>

    <!-- 나이 -->
    <div class="form-group mb-3">
        <label class="form-label">나이</label>
        <input type="number" class="form-control" v-model.number="user.age" min="0" max="150" />
    </div>

    <!-- 성별 -->
    <div class="form-group mb-3">
        <label class="form-label">성별</label>
        <div>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" id="male" value="Male" v-model="user.gender" />
            <label class="form-check-label" for="male">남자</label>
        </div>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" id="female" value="Female" v-model="user.gender" />
            <label class="form-check-label" for="female">여자</label>
        </div>
        </div>
    </div>

    <!-- 휴대전화번호 -->
    <div class="form-group mb-3">
        <label class="form-label">휴대전화번호</label>
        <input type="text" class="form-control" v-model="user.phone" placeholder="010-1234-5678" />
    </div>

    <!-- 주소 -->
    <div class="form-group mb-4">
        <label class="form-label">주소</label>
        <input type="text" class="form-control" v-model="user.address" placeholder="주소를 입력하세요" />
    </div>

    <div class="form-group mb-3">
        <label class="form-label">역할 선택</label>
        <select class="form-select" v-model="user.role" required>
            <option disabled value="">역할을 선택하세요</option>
            <option value="CUSTOMER">일반 사용자</option>
            <option value="SELLER">판매자</option>
            <option value="PHARMACIST">약사</option>
        </select>
    </div>

    <!-- 버튼 -->
    <div class="d-flex justify-content-end gap-2">
        <button class="btn btn-success" @click="submitUpdate(toRaw(user))">수정하기</button>
    </div>
    </div>
</template>

<script setup>
import { reactive, toRaw } from 'vue';
import apiClient from '@/api';

const user = reactive({
    name: '',
    age: '',
    gender: '',
    role: '',
    phone: '',
    address: ''
});

// 정규표현식: 전화번호 형식 (000-0000-0000)
const phoneRegex = /^\d{3}-\d{4}-\d{4}$/;

const submitUpdate = async (InfoData) => {

    // 나이 유효성 검사 (0~150)
    if (InfoData.age < 0 || InfoData.age > 150) {
        alert('나이는 0에서 150 사이로 입력해주세요.');
        return;
    }

    // 전화번호 형식 검사
    if (!phoneRegex.test(InfoData.phone)) {
        alert('전화번호는 000-0000-0000 형식으로 입력해주세요.');
        return;
    }

    try {
        const response = await apiClient.post('/user/my-page', InfoData);
        if (response.status === 200) {
        alert('회원 정보가 성공적으로 수정되었습니다.');
        }
    } catch (error) {
        alert('정보 수정 중 오류가 발생했습니다.');
        console.error(error);
    }
    };
</script>

<style scoped>
.info-container {
    max-width: 600px;
    margin: 0 auto;
    background-color: #fff;
    padding: 2rem;
    border-radius: 10px;
    box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.05);
}
</style>
