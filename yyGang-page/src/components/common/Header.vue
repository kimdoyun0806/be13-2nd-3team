<template>
    <header class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">

    <!-- 사이드바 토글 버튼 -->
    <button @click="$emit('toggle-sidebar')" id="list-btn">
        <i class="bi bi-list col-md-1 ms-3"></i>
    </button>

    <!-- 로고 -->
    <RouterLink to="/" class="align-items-center mb-3 mb-md-0 me-auto link-body-emphasis text-decoration-none ms-5">
        <img src="https://yygang-bucket.s3.ap-northeast-2.amazonaws.com/mainLogo.png" alt="mainLogo" style="width: 80px;">
    </RouterLink>

    <!-- 네비게이션 -->
    <ul class="nav nav-pills align-self-center justify-content-around">
        <li class="nav-item">
        <RouterLink class="nav-link" :to="{ name: 'supplement' }">제품 정보</RouterLink>
        </li>
        <li class="nav-item">
        <RouterLink class="nav-link" :to="{ name: 'supplement' }">추천 영양제 찾기</RouterLink>
        </li>
        <li class="nav-item">
        <RouterLink class="nav-link" :to="{ name: 'questionboard' }">약 질문하기</RouterLink>
        </li>
        <li class="nav-item">
        <RouterLink class="nav-link" :to="{ name: 'board' }">자유 게시판</RouterLink>
        </li>
    </ul>

    <!-- 로그인/회원가입/아이콘 -->
    <div class="col-md-4 text-center align-self-center">
        <ul class="nav nav-pills align-self-center justify-content-end">
        <li class="nav-item" v-if="!isLoggedIn">
            <RouterLink class="nav-link" :to="{ name: 'login' }">로그인</RouterLink>
        </li>
        <li class="nav-item" v-if="!isLoggedIn">
            <RouterLink class="nav-link" :to="{ name: 'join' }">회원가입</RouterLink>
        </li>
        <li class="nav-item" v-if="isLoggedIn">
            <span class="nav-link">"{{ userInfo.username }}"님 안녕하세요!</span>
        </li>
        <li class="nav-item" v-if="isLoggedIn">
            <button class="nav-link" @click="logout">로그아웃</button>
        </li>

        <li class="nav-item">
            <RouterLink class="nav-link" :to="{ name: 'my-page' }"><i class="bi bi-person"></i></RouterLink>
        </li>
        <li class="nav-item">
            <RouterLink class="nav-link" to="#"><i class="bi bi-search"></i></RouterLink>
        </li>
        <li class="nav-item">
            <RouterLink class="nav-link" :to="{ name: 'cart' }"><i class="bi bi-cart"></i></RouterLink>
        </li>
        <li class="nav-item">
            <RouterLink class="nav-link" to="#"><i class="bi bi-heart"></i></RouterLink>
        </li>
        
        </ul>
    </div>

    </header>
</template>

<script setup>
import { useAuthStore } from '@/stores/auth';
import { computed } from 'vue';

const authStore = useAuthStore();

const isLoggedIn = computed(() => authStore.isLoggedIn);
const userInfo = computed(() => authStore.userInfo);

const logout = () => {
    if (confirm('정말로 로그아웃하시겠습니까?')) {
    authStore.logout();
    }
};
</script>

<style scoped>
.nav-link {
    color: black !important;
}
</style>
