def fibonacci(n)
  if n < 2
    return 1
  end
  return fibonacci(n - 2) + fibonacci(n - 1)
end

puts fibonacci(40)