// モーダルを表示する関数
function showModal() {
    $('#myModal').modal('show');
    // 数秒後にモーダルを非表示にする
    setTimeout(function(){
      $('#myModal').modal('hide');
    }, 3000); // 3000ミリ秒 = 3秒
  }

  // ページ読み込み時に最初のモーダルを表示
  $(document).ready(function(){
    showModal();
    // 一定間隔でモーダルを繰り返し表示
    setInterval(showModal, 3000); // 5000ミリ秒 = 5秒
  });