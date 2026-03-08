;;подключение библиотек (лучше дайте полинковать либы в C++ ;(
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

;; Генерация вектора и конвертация его в список(так будто бы легче) 
(def q (apply list (gen/generate (gen/vector gen/int n))))

(println "список:" q))


(loop [m q]
  (println "Меню:")
  (println "0-выход")
  (println "1-сортировка по возрастанию")
  (println "2-фильтрация списка (фильтруем только ч делящиеся на ч)")
  (let [input (read-line)]
    (cond
      (= input "0") (println "Выход...") ; Просто выходим

      (= input "1")
      (do
        (println "отсортированный список" (sort m))
        (recur m)) ; Возврат в меню  









      (= input "2")
      (do
        (print "Введите число n (делитель): ") (flush)
        (let [n (Integer/parseInt (read-line))
              ;; 2. (filter ...) - оставляем только те, где остаток от деления на n равен 0
              result (filter (fn [x] (= (mod x n) 0)) (sort m))]

          (println "\n--- Результат фильтрации ---")
          (println "Исходный список (отсортирован):" (sort m))
          (println "Числа, которые делятся на" n "без остатка:")

          (if (empty? result)
            (println "Таких чисел в списке не нашлось.")
            (println result)))
        (recur m))


      ;;попытался реализовать функционал как у метода .partition в ruby
      (= input "3")
      (do
        (print "Введите число n (делитель): ") (flush)
        ;; group-by
        (let [n (Integer/parseInt (read-line))
              ;; ВАЖНО: 0 должен быть ВНУТРИ скобок функции =, 
              ;; чтобы получилось "равно ли (mod x n) нулю"
              group (group-by (fn [x] (= (mod x n) 0)) (sort m))

              ;; выделим массивы с true и false
              result (get group true)
              not_result (get group false)]
          
          (println "список сгруппированный" group) 

          (if (empty? result)
            (println "Таких чисел в списке не нашлось")
            (println "Делятся нацело:" result))

          (if (empty? not_result)
            (println "Все числа списка нашлись")
            (println "НЕ делятся нацело:" not_result)))
        (recur m))

      :else
      (do
        (println "Неизвестная команда!")
        (recur m))))) ; Возврат в меню