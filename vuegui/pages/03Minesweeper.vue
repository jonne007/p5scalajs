<template>
<div>
  <div><Nav></div>
  <div id=p5id></div>
  <div>
    todo
    <ul>
      <li>timer</li>
      <li>end detection</li>
      <li>right click mine marker</li>
    </ul>

  </div>
</div>
</template>

<script>
export default {
  mounted: function () {
    const b = new Minesweeper();
    new p5(b.sketch, "p5id");
  },
};
</script>

<style scoped>
</style>