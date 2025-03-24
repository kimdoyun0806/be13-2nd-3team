<template>
    <main>
    <JoinForm
        submit-button-text="회원 가입"
        :init-form-data="initFormData"
        @form-submit="formSubmit"
    />
    </main>
</template>

<script setup>
import { reactive } from 'vue';
import { useRouter } from 'vue-router';
import JoinForm from '@/components/forms/JoinForm.vue';
import apiClient from '@/api';

const router = useRouter();

const initFormData = reactive({
    email: '',
    userPwd: '',
    userPwd2: '',
    name: '',
    age: null,
    gender: '',
    phone: '',
    address: ''
});

const formSubmit = async (formData) => {
    try {
    const response = await apiClient.post('/user/join', formData);

    if (response.data.code === 201 || response.status === 201) {
        alert('회원가입이 완료되었습니다!');
        router.push({ name: 'main' });
    }
    } catch (error) {
    if (error.response.data.code === 400) {
        alert(error.response.data.message);
    } else if (error.response.data.code === 409) {
        alert(error.response.data.message)
    } 
    else {
        alert('회원가입 중 오류가 발생했습니다.');
    }
    }
};
</script>
