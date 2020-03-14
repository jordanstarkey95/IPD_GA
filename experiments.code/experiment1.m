% Experiment 1 - Memory Length

% Average average fitness
figure;
hold on;
for k=1:6
  data = dlmread(strcat('memory_length-', num2str(k), '_BestAndAverageGeneration.csv'), ',', 0, 0);
  gen = data(:, 1);
  avg = data(:, 2);
  best = data(:, 3);
  stdAvg = data(:, 4);
  stdBest = data(:, 5);
  errorbar(gen, avg, stdAvg);
end

title('Average average fitness for different memory lengths');
xlabel('Generation');
ylabel('Average fitness');
legend('1','2','3','4','5','6','Location','NorthEast');
saveas(gcf,'experiment1.png')