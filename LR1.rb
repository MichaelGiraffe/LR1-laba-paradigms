#потом преобразовать через ООП
puts "введите кол-во случайных чисел"
n=gets.to_i

#тут можно было бы сократить написав m=Array.new(n) {rand(1000)}
m=[]
n.times do
    m << rand(1000)
end
puts m.inspect

while n>0
    puts "выберите действие от 1 до 3"
    n=gets.to_i
    if n==1
        puts m.sort!.inspect
    elsif n==2
        puts m.sort!.reverse.inspect
    elsif n==3#фильтрация
        evens,odds=m.partition{|q| q.even?}
        puts "evens=#{evens.inspect}"
        puts "odds=#{odds.inspect}"
    end
end
