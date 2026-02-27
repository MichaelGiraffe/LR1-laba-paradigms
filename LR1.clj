;;подключение библиотек (лучше дайте полинковать либы в C++ ;(   )
(require '[clojure.test.check :as tc])
(require '[clojure.test.check.generators :as gen])
(require '[clojure.test.check.properties :as prop])

;; Пример генерации случайного числа
(println "Случайное число:" (gen/generate gen/int))

(println "Привет из Clojure!")

;; это я создал вектор как-то через функцию
;;при том massive-это имя массива (выглядит проклято)
;;(def massive-vector [1 2 3])
;;(println ((conj massive-vector 4 5) 3))

;;это ввод числа(вместо какого нибудь cin или gets...)
(let[input (read-line) n (Integer/parseInt input)]

;; Генерируем вектор и конвертируем его в список
(def m (apply list (gen/generate (gen/vector gen/int n))))

(println "список:" m))


(loop []
  (println "Меню:")
  (println "0-выход")
  (println "1-сортировка по возрастанию")
  (println "2-подсчет чисел")
  (let [input (read-line)]
    (cond
      (= input "0") (println "Выход...") ; Просто выходим

      (= input "1")
      (do
        (print "Сколько чисел? ") (flush)
        (let [n-str (read-line)
              n (Integer/parseInt n-str)
              m (apply list (gen/generate (gen/vector gen/int n)))]
          (println "Ваш список:" m))
        (recur)) ; Возврат в меню

      :else
      (do
        (println "Неизвестная команда!")
        (recur))))) ; Возврат в меню