import React from "react";

function Asynchronous(props) {
  // 콜백(callback):함수에 함수를 넘겨서 내가 의도한 순서대로 동작하게 하는 것.
  /* 동기(synchronous):순서대로 동작함
    비동기(asynchronous):순서대로 동작하지 않음
    콜백을 사용하는 이유: 비동기처리는 할거지만 동기처리가 되게끔 하는척 하려고
    비동기:기다리지 않는다
    콜백을 쓰는 이유_최종: 비동기처리를 하지만 비동기처리 안에서 동기처리가 일어나야 함
    콜백안에: 함수가 들어감, 비동기처리 안에서, 비동기 동작안의 동작들이 내가 정한 순서대로(동기적으로 작동하게끔) 동작하게 하고싶을 때!
    axios요청을 던졌다가 응답이 오면 then이 실행됨. 하나의 비동기, 응답이 와야 then이 실행되고 axios의 then이 3개일 때?
  */
  let num = 0;

  //setTimeout:비동기함수의 대표적인 예
  const handleClick = () => {
    num++;

    const click1 = (num) => {
      console.log(`${num} click11111!`);
    };
    const click1After = () => {
      console.log("click1111111 다음에 실행");
    };
    const click2 = (num) => {
      console.log(`${num} click2222222!`);
    };
    const click2After = () => {
      console.log("click22222222222 후에 실행,,,!");
    };
    const clickFx = (fx1, fx2) => {
      fx1(num);
      fx2();
    };

    // setTimeout 3번째 인자:3초 후 실행될 때 매개변수로 전달한 함수가 다시 콜백의 매개변수로 들어감
    setTimeout(clickFx, Math.random(3) * 1000, click1, click1After); //비동기

    // 이자리에 click1After();이 있으면 내 의도대로 1이후 after가 동작하는게 아니라, 이자리의 click1after가 젤먼저 실행됨.

    setTimeout(clickFx, Math.random(3) * 1000, click2, click2After); //비동기
  }
  const handleClick2 = () => {
    const p1 = new Promise((resolve, reject) => {
      const num = Math.random(4);
      if(Math.round(num % 2, 0) === 0) {
        resolve("짝수");
      } else {
        reject(new Error("홀수"))
      }
    })
    p1.then(result => { //resolve가 일어나면 then
      console.log(result);
      return "첫번째 then의 리턴"; //이 return값이 아래의 console.log에 들어간다.
    }).then(result => {
      console.log(result);
    }).catch(error => {
      console.log(error);
    });
  }
//async : promise를 편하게 쓸 수 있다.
  const handleClick3 = () => {

    //이건 promise. 비교해볼까?
    const printUser2 = () => {
      return new Promise((resolve, reject) => {
        resolve("유저2");
        reject(new Error("오류2"));
      });
    }
    printUser2().then(r => console.log(r));
    printUser2().catch(r => console.log(r));
    // const printUser = async function() { }
    const printUser = async () => {
      try {
        // await : 비동기안에서 비동기처리할 때.
        // await밑에있는 코드가 printUser2 메서드가  기다린다.
        await printUser2().then((r) => {
          console.log(r);
        });
        throw new Error("오류처리"); //오류처리 : throw
      } catch (error) {
        console.log(error);
      }
      return "유저1";
    }
    printUser().then(r => console.log(r)); //promise처럼 쓸 수 있다.
  }
  return (
    <div>
      <button onClick={handleClick}>클릭1</button>
      <button onClick={handleClick2}>클릭2</button>
      <button onClick={handleClick3}>클릭3</button>
    </div>
  );
}

export default Asynchronous;
