<template>
 <div class="tarot-page">
    <div class="message-input">
      <input type="text" v-model="message" placeholder="请输入您的问题" />
      <button @click="interpretCards">抽牌</button>
    </div>

    <div class="cards-display">
      <div v-for="card in cards" :key="card.title" class="card">
        <h4>{{ card.title }}</h4>
        <p>{{ card.explain }}</p>
        <div>
          <strong>work: </strong>{{ card.work }}
          <br>
          <strong>love: </strong>{{ card.love }}
          <br>
          <strong>friend: </strong>{{ card.friend }}
          <br>
          <strong v-if="card.affection">affection: </strong><span v-if="card.affection">{{ card.affection }}</span>
        </div>
      </div>
    </div>

    <div class="tarot-response">
      <textarea v-model="tarotResponse" placeholder="塔罗解读结果" readonly></textarea>
    </div>
  </div>

</template>

<script>

export default {
  data() {
    return {
      message: '',
      cards: [],
      tarotResponse: ''
    };
  },
  methods: {
    interpretCards() {
      // 基于抽到的牌解读塔罗
      this.$axios.get('/zhipu/tarot-three-cards', { params: { message: this.message } })
        .then(response => {
          this.tarotResponse = response.data.interpretation;
          this.cards = response.data.tarotCards	;
        })
        .catch(error => {
          console.error('Error interpreting cards:', error);
        });
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  body{
    background: #000000;
    font-family: "Helvetica Neue","Hiragino Sans GB","Microsoft YaHei","\9ED1\4F53",Arial,sans-serif;
    color: #222;
    font-size: 12px;

  }
    .tarot-page {
    display: flex;
    flex-direction: column;
    justify-content: center;
    min-height: 80vh; /* 让页面至少填满整个视口高度 */
    padding: 20px; /* 添加一些内边距 */
    max-width: 1200px;
    margin: -100px auto; /* 居中 */
  }
   .message-input input {

    width: 100%;
    padding: 16px;
    margin-bottom: 20px;
    height: 100px;

    }
   .cards-display .card {
    border: 2px solid #3aff04;
    padding: 10px;
    margin-bottom: 10px;
    box-shadow: 0 2px 5px rgba(0,0,0,0.1); /* 添加轻微的阴影效果 */
    background-color: #fff;
  }
   .tarot-response textarea {
    width: 100%;
    justify-content: center;
    flex-direction: column;
    height: 400px;
    padding: 10px;
  }

</style>
