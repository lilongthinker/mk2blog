function fibonacci(n)
  if n < 2 then
    return 1
  end
  return fibonacci(n - 2) + fibonacci(n - 1)
end

io.write(fibonacci(40), "\n")