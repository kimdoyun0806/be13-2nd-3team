<template>
    <div>

    <div class="container-fluid my-page-wrapper">
        <div class="row">
        <!-- 사이드바 -->
        <div class="col-2 sidebar text-start ps-4 pt-5 border-end">
            <h5 class="fw-bold mb-4">마이 페이지</h5>

            <div class="mb-5">
            <p class="fw-bold">나의 정보</p>
            <ul class="list-unstyled small">
                <li class="mb-2"><a href="#">회원 정보 수정</a></li>
                <li class="mb-2"><a href="#">주문 내역</a></li>
                <li><a href="#">계좌 관리</a></li>
            </ul>
            </div>

            <div>
            <p class="fw-bold">나의 활동</p>
            <ul class="list-unstyled small">
                <li class="mb-2"><a href="#">상품문의 내역</a></li>
                <li class="mb-2"><a href="#">내가 쓴 리뷰</a></li>
                <li class="mb-2"><a href="#">나의 글 내역</a></li>
                <li><a href="#">나의 답글 내역</a></li>
            </ul>
            </div>
        </div>

        <!-- 본문 -->
        <div class="col-10 p-5">
            <!-- 환영 메시지 박스 -->
            <div class="welcome-box d-flex justify-content-between align-items-center p-4 mb-5">
            <div>
                <p class="fw-bold mb-2">{{ userInfo.username }} 님 </p>
            <p>오늘도 건강한 하루 보내세요!</p>
            </div>
            <img src="@/assets/icon/SupplementCharactor.png" alt="캐릭터" class="welcome-character" />
            </div>
            <RouterView></RouterView>
        </div>
        </div>
    </div>

    <Footer></Footer>
    </div>
</template>


<script setup>
import { computed } from 'vue';
import { useAuthStore } from '@/stores/auth';
import Header from '@/components/common/Header.vue';
import Footer from '@/components/common/Footer.vue';

const authStore = useAuthStore(); 
const userInfo = computed(() => authStore.userInfo); 
</script>


<style scoped>
    .my-page-wrapper {
    background-color: #fff;
    min-height: 100vh;
    }

    .sidebar {
    background-color: #fff;
    border-right: 1px solid #e0e0e0;
    }

    .sidebar ul li a {
    color: #000;
    text-decoration: none;
    }

    .sidebar ul li a:hover {
    text-decoration: underline;
    }
    .welcome-box {
    background-color: #ffffff;
    border: 1px solid #ddd;
    border-radius: 15px;
    box-shadow: 3px 3px 8px rgba(0, 0, 0, 0.1);
    min-height: 120px;
    width: 100%;
    max-width: 850px;
    margin: 0 auto;  
    padding: 24px;
}

    .welcome-character {
    width: 70px;
    height: auto;
    }

</style>
