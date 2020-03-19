% Experiment 4

crossover_rate = 0.5;
mutation_rate = 0.001;
memory_length = 1;
number_of_games = 64;

% Average average fitness
crossover_rate = crossover_rates(i);
mutation_rate = mutation_rates(j);
data=dlmread(strcat('..\experiment-2crossover_rate-', num2str(crossover_rate, 1), 'mutation_rate-', num2str(mutation_rate, 3), 'memory_length-', num2str(memory_length), 'number_of_games-', num2str(number_of_games),  '_BestAndAverageGeneration.csv'), ',', 0, 0);
gen = data(:, 1);
avg = data(:, 2);
best = data(:, 3);
stdAvg = data(:, 4);
stdBest = data(:, 5);

figure;
errorbar(gen, avg, stdAvg);
hold on;
errorbar(gen, best, stdBest);
title('Average average fitness and average best fitness');
xlabel('Generation');
ylabel('Average fitness');
legend('Average','Best','Location','NorthEast');
saveas(gcf,'experiment4.png')