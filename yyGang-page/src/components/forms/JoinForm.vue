<template>
    <form @submit.prevent="submitClick" class="join-form">
    <div class="text-center mb-4">
        <img data-v-dc13a441="" src="https://yygang-bucket.s3.ap-northeast-2.amazonaws.com/mainLogo.png" alt="mainLogo" style="width: 80px;">
    </div>


    
    <h2 class="text-center mb-4">회원 가입</h2>
    <hr class="mb-4" />

    <div class="mb-3">
        <label for="email">이메일</label>
        <input type="email" v-model.trim="formData.email" class="form-control" placeholder="이메일(이메일 형식)" required />
    </div>

    <div class="mb-3">
        <label for="gender">성별</label>
        <select v-model="formData.gender" class="form-select" required>
        <option disabled value="">선택 ▼</option>
        <option value="Male">남성</option>
        <option value="Female">여성</option>
        </select>
    </div>

    <div class="mb-3">
        <label for="userPwd">비밀번호</label>
        <input type="password" v-model.trim="formData.userPwd" class="form-control" placeholder="영문, 숫자, 특수기호 조합" required />
    </div>

    <div class="mb-3">
        <label for="userPwd2">비밀번호 확인</label>
        <input type="password" v-model.trim="formData.userPwd2" class="form-control" placeholder="비밀번호를 다시 입력해 주세요" required />
    </div>

    <div class="mb-3">
        <label for="name">이름</label>
        <input type="text" v-model.trim="formData.name" class="form-control" required />
    </div>

    <div class="mb-3">
        <label for="age">나이</label>
        <input type="number" v-model.number="formData.age" class="form-control" />
    </div>

    <div class="mb-3">
        <label for="phone">전화번호</label>
        <input type="text" v-model.trim="formData.phone" class="form-control" placeholder="010-1234-5678" />
    </div>

    <div class="mb-4">
        <label for="address">주소</label>
        <input type="text" v-model.trim="formData.address" class="form-control" placeholder="주소 입력" />
    </div>

    <button type="submit" class="btn btn-success w-100 py-2">회원 가입</button>
    </form>
</template>

<script setup>
import { reactive, toRaw, watch } from 'vue';

const formData = reactive({});
const props = defineProps({
    initFormData: Object
});

const emit = defineEmits(['form-submit']);

const submitClick = () => {
    emit('form-submit', toRaw(formData));
};

watch(
() => props.initFormData,
(newFormData) => {
    Object.assign(formData, newFormData);
},
{ immediate: true }
);</script>

<style scoped>
.join-form {
    max-width: 420px;
    margin: auto;
    padding: 1rem;
    font-family: 'Arial', sans-serif;
}
</style>
