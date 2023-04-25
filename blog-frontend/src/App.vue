<template>
    <div id="app">
        <nav>
            <router-link to="/">Home</router-link>
            |
            <router-link to="/about">About</router-link>
            |
            <router-link to="/login">Login</router-link>
            |
            <router-link to="/blog/add">Add</router-link>
            |
        </nav>
        <router-view/>
    </div>
</template>

<style>

</style>
<script setup>
</script>